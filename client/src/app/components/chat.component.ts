import { Component, OnInit, inject } from '@angular/core';
import { OllamaService } from '../ollama.service';
import { Message } from '../models';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {

  messages: Message[] = []
  messageForm!: FormGroup

  private fb = inject(FormBuilder)
  private ollamaSvc = inject(OllamaService)

  ngOnInit(): void {
    this.messageForm = this.createMessageForm()
  }

  sendMessage() {
    if (this.messageForm.valid) {
      const text = this.messageForm.value.text
      this.messages.push({
        text: text,
        sender: 'User',
        timestamp: new Date()
      })
      this.ollamaSvc.chatWithOllama(text)
        .then(response => {
          this.messages.push({
            text: response.message,
            sender: 'Ollama',
            timestamp: new Date()
          })
        })
      this.messageForm = this.createMessageForm()
    }
  }

  private createMessageForm(): FormGroup {
    return this.fb.group({
      text: this.fb.control<string>("", [ Validators.required, Validators.minLength(3) ])
    })
  }
}

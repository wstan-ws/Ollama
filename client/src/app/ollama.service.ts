import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { lastValueFrom } from "rxjs";

@Injectable()
export class OllamaService {

    private http = inject(HttpClient)

    chatWithOllama(message: string): Promise<any> {
        message = message.trim()
        const options = message ? {params: new HttpParams().set('message', message)} : {}
        const url = 'http://localhost:8080/api'
        return lastValueFrom(this.http.get(url, options))
    }
}
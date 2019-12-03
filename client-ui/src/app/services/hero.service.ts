import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {Hero} from '../models/hero';
import {MessageService} from './message.service';

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getHeroes(): Observable<Hero[]> {
    this.messageService.add('HeroService: fetched heroes');
    return this.http.get<Hero[]>(environment.serverUrl + 'heroes');
  }

  getHero(id: number): Observable<Hero> {
    this.messageService.add(`HeroService: fetched hero id=${id}`);
    return this.http.get<Hero>(environment.serverUrl + 'hero/' + id);
  }

  saveHero(hero: Hero): Observable<any> {
    this.messageService.add('HeroService: saved hero');
    return this.http.post(environment.serverUrl + 'hero/save', hero);
  }
}

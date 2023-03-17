import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Room } from './room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private baseURL = "http://localhost:9001/room/all";
  constructor(private httpClient: HttpClient) { }

  getAllRooms(): Observable<Room[]> {
    return this.httpClient.get<Room[]>(`${this.baseURL}`);
  }

  addRoom(payload: any): Observable<any> {
    return this.httpClient.post<any>(`http://localhost:9001/room/addroom`, payload);
  }

  updateRoom(room: Room): Observable<Room> {
    return this.httpClient.put<Room>(`http://localhost:9001/room/updateroom`, room);
  }

  getBynumber(roomNumber: number): Observable<Room> {
    console.log(roomNumber);
    return this.httpClient.get<Room>(`http://localhost:9001/room/${roomNumber}`);
  }

  deleteRoom(roomNumber: number): Observable<Room> {
    return this.httpClient.delete<Room>(`http://localhost:9001/room/deleteroom/${roomNumber}`);
  }

}





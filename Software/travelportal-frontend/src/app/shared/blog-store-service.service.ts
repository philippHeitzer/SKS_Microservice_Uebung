import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Blog } from './Blog';
import { AttractionStats } from './AttractionStats';

@Injectable({
  providedIn: 'root'
})
export class BlogStoreServiceService {

  private api= 'http://localhost:5555/travelportal';

  constructor(private http: HttpClient){

  }

  getAllBlogs(): Observable<Blog[]> {
    return this.http.get<Blog[]>(`${this.api}/blog/resources/blogs`)
    .pipe(
      catchError(this.errorHandler)
    );
  }

  getSingle(id : string): Observable<Blog> {
    return this.http.get<Blog>(
      `${this.api}/blog/resources/blogs/${id}`
    ).pipe(
      catchError(this.errorHandler)
    );
  }

  getStats(listSize : string) : Observable<AttractionStats[]> {
    return this.http.get<AttractionStats[]>(
      `${this.api}/stats/resources/stats/${listSize}`
    ).pipe(
      catchError(this.errorHandler)
    );
  }

  createBlog(blog: Blog): Observable<any> {
    return this.http.post(
      `${this.api}/blog/resources/blogs`,
      blog,
      { responseType: 'text' }
    ).pipe(
      catchError(this.errorHandler)
    );
  }


  private errorHandler(error: HttpErrorResponse): Observable<any> {
    console.error('Fehler aufgetreten!');
    return throwError(error);
  }

}

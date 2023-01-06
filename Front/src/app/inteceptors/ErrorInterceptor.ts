import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from "rxjs/operators";


@Injectable()
export class ErrorInterceptor  implements HttpInterceptor {
  constructor() {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      return next.handle(request)
          .pipe(
              catchError((error: HttpErrorResponse) => {
                  let errorMsg = '';
                  // if ((error.status === 200 && error.url && error.url.includes('/cas/login')) || error.status === 401 || error.status === 403) {
                  //   window.location.href = 'https://localhost:8443/cas/login?service=https%3A%2F%2Flocalhost%3A8443%2Fesports%2Fucl%2Flogin%2Fcas';
                  // }
                  //   return throwError(error);
                  if (error.error instanceof ErrorEvent) {
                      console.log('This is client side error');
                      errorMsg = `Error: ${error.error.message}`;
                  } else {
                      console.log('This is server side error');
                      errorMsg = `Error Code: ${error.status},  Message: ${error.message}`;
                  }
                  console.log(errorMsg);
                  return throwError(errorMsg);
              })
          )
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../product';

@Injectable({
  providedIn: 'root'
})
export class Productservice {

  constructor(private http: HttpClient) {}
  private baseUrl = environment.localBaseUrl;

  public getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/products`);
  }

  public addProduct(product : Product): Observable<Product> {
    return this.http.post<Product>(`${this.baseUrl}/products`,product);
  }

  public updateProduct(id:number, product : Product): Observable<Product> {
    return this.http.put<Product>(`${this.baseUrl}/products/${id}`,product);
  }

  public deleteById(id:number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/products/${id}`);
  }

  public getProductsWithPagination(currentPage : number, pageSize : number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/products/pagination/${currentPage}/${pageSize}`);
  }
}

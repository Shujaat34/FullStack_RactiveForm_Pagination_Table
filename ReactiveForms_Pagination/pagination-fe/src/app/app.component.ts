import { animate } from '@angular/animations';
import { HttpErrorResponse } from '@angular/common/http';
import { Component,OnInit,ViewChild } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DialogComponent } from './dialog/dialog.component';
import { Product } from './product';
import { Productservice } from './services/product.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  pageSize !: number;
  currentPage !: number;

  title = 'pagination-fe'; 

  ngOnInit(): void {
    this.getAllProducts();
  }

  displayedColumns: string[] = ['id', 'name', 'category','durability',
                                'description', 'price','comment','date','action'];
  dataSource !: MatTableDataSource<Product>;

  @ViewChild(MatPaginator) paginator !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;


  constructor(public dialog: MatDialog, private productService : Productservice) {}

  openDialog() {
    this.dialog.open(DialogComponent, {
      width:'30%'
    }).afterClosed().subscribe(val=>{
      if(val === 'Save'){
          this.getAllProducts();
      }
    });
  }

  editProduct(row : any){
    this.dialog.open(DialogComponent,{
      width : '30%',
      data : row,
    }).afterClosed().subscribe(val=>{
      if(val === 'Update'){
        this.getAllProducts();
      }
    });
  }

  delete(row : any){
    this.productService.deleteById(row.id).subscribe(
      (res: any) => {     
        this.getAllProducts();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  getAllProducts(){
    this.productService.getAllProducts().subscribe(
      (response: Product[]) => {        
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        // Swal.fire('Success','User is Registered Successfully!','success');
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  pageChanged(event : PageEvent){
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    
    this.productService.getProductsWithPagination(this.currentPage,this.pageSize).subscribe(
      (res: any) => {     
        this.dataSource = new MatTableDataSource(res.response.content);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );

  }
}

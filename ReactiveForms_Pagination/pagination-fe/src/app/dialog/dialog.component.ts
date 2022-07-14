import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup,FormBuilder,Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Product } from '../product';
import { Productservice } from '../services/product.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  durabilitylist : string [] = ['2 years','4 years','6 years'];

  productForm !: FormGroup;

  product : Product = new Product();

  actionBtn : string = 'Save';

  constructor(private formBuilder : FormBuilder,
    private productService : Productservice, 
    private matdialogRef : MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public editData : any) { }

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      name : ['',Validators.required],
      category : ['',Validators.required],
      durability : ['',Validators.required],
      description : ['',Validators.required],
      price :['',Validators.required],
      comment :['',Validators.required],
      date :['',Validators.required]
    });

    if(this.editData){
      this.productForm.controls['name'].setValue(this.editData.name);
      this.productForm.controls['category'].setValue(this.editData.category);
      this.productForm.controls['durability'].setValue(this.editData.durability);
      this.productForm.controls['description'].setValue(this.editData.description);
      this.productForm.controls['price'].setValue(this.editData.price);
      this.productForm.controls['comment'].setValue(this.editData.comment);
      this.productForm.controls['date'].setValue(this.editData.date);

      this.actionBtn = 'Update';
      
    }
   
  }

  addProduct(){

    //Edit functionality
    if(this.editData){
      if(this.productForm.valid){
        this.product = this.productForm.value;
        this.productService.updateProduct(this.editData.id,this.product).subscribe(
        (response: Product) => {
          alert("Updated Successfully");
          this.productForm.reset;
          this.matdialogRef.close('Update');
          // Swal.fire('Success','User is Registered Successfully!','success');
        },
        (error: HttpErrorResponse) => {
          console.log(error.message);
        }
      );
      }

    }else{
      //Save Functionality
      if(this.productForm.valid){
          this.product = this.productForm.value;
          this.productService.addProduct(this.product).subscribe(
          (response: Product) => {
            alert("Added Successfully");
            console.log(response);
            this.productForm.reset;
            this.matdialogRef.close('Save');
            // Swal.fire('Success','User is Registered Successfully!','success');
          },
          (error: HttpErrorResponse) => {
            console.log(error.message);
          }
        );
      }
    }    
  }

}

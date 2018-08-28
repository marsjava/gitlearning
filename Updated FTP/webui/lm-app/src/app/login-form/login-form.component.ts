import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule, FormsModule, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  // styleUrls: ['./login-form.component.css']
  styles: [`
  input.ng-invalid {border-left: 10px solid red;}
  input.ng-valid {border-left: 10px solid green;}
  `]
})
export class LoginFormComponent implements OnInit {
  employeeId: number;
  userForm: FormGroup;
  constructor(fb: FormBuilder, private route: Router, private activeRoute: ActivatedRoute) {
    this.employeeId = this.activeRoute.snapshot.params['empId'];
    
    this.userForm = fb.group({
      empId: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(5)]),
    
    });

    this.userForm.patchValue({ empId: this.employeeId });}

  onLogin() {
    this.route.navigate(['/dashboard', this.employeeId]);
  }
  onCancel() {
    this.route.navigate(['/table']);
  }
  ngOnInit() {
  }
}

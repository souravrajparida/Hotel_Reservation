import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserRegistration } from 'src/app/models/user-reg';
import { AuthService } from 'src/app/services/auth.service';
import { RegistrationService } from 'src/app/services/registration.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  msg: string = ''
  color: String = ''
  return: string = '';
  fieldErrors: string[] = []

  constructor(
    private registerService: RegistrationService,
    private authService: AuthService,
    public router: Router,
  ) { }

  ngOnInit(): void {
  }

  userModel = new UserRegistration(0,"","","","","", "")

  user = new User("","")
  
  handleReset() {
    this.msg = ""
    this.fieldErrors = []
  }

  handleLogin() {    
    // process-login
    this.authService.login(this.user)
      .subscribe(
        // if login was successful
        data => {
          this.authService.setSession(data);
          this.router.navigateByUrl("room");
        },
        // if login failed, display the error
        error => {
          try {
            this.fieldErrors = JSON.parse(error.error).fieldErrors;
          } catch (error) {
            this.msg = "Session is over,please login!"
            console.log(this.msg);
          }
        }
      );
  }

  // handle the registration process
  handleRegister() {    
    // process-register
    this.registerService.register(this.userModel)
      .subscribe(
        // if login was successful
        data => {
         // this.authService.setSession(data);
          this.router.navigateByUrl("login");
        },
        // if login failed, display the error
        error => {
          try {
            this.fieldErrors = JSON.parse(error.error).fieldErrors;
          } catch (error) {
            this.msg = "Please register again!"
            console.log(this.msg);
          }
        }
      );
    
  }

}

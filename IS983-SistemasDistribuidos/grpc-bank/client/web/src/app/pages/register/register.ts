import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.grpc.service';
import { UserService } from '../../services/user.grpc.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register.html',
  styleUrls: ['./register.scss'],
})
export class Register {
  protected registerForm: FormGroup;
  protected router = inject(Router);
  protected authService = inject(AuthService);
  protected userService = inject(UserService)

  constructor(private fb: FormBuilder) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  protected get name() {
    return this.registerForm.get('name');
  }

  protected get email() {
    return this.registerForm.get('email');
  }

  protected get password() {
    return this.registerForm.get('password');
  }

  protected async submit() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    const { name, email, password } = this.registerForm.value;
    try {
      const user = await this.userService.createUser(email, password, name);
      if (!user) return;

      const id = Number(user.getId());
      if(Number.isNaN(id)) return;

      if (id === 1) {
        await this.router.navigate(['dashboard']);
      } else {
        await this.router.navigate(['account']);
      }
    } catch (error) {
      console.error('Register error', error);
    }
  }
}

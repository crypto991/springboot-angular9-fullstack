import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';
import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';

import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(
        private router: Router,
        private authService: AuthService,
        private tokenStorage: TokenStorageService
    ) { }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

        let url: string = state.url;
        return this.checkLogin(url);
    }


    private checkLogin(url: string): boolean {
        if (this.authService.isLoggedIn()) {
            return true;
        }
        this.authService.redirectToUrl = url;
        this.router.navigate(['/login']);
        return false;
    }

}
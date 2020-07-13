import { AccountService } from './../_services/account.service';
import { Farm } from './../classes/farm';
import { TokenStorageService } from './../_services/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { Account } from '../classes/account';

@Component({
  selector: 'app-list-user-accounts',
  templateUrl: './list-user-accounts.component.html',
  styleUrls: ['./list-user-accounts.component.css']
})
export class ListUserAccountsComponent implements OnInit {

  accounts: Account[];
  account: Account;
  farm: Farm;
  userId = this.tokenStorage.getUserID();

  constructor(private accountService: AccountService,
    private tokenStorage: TokenStorageService) {

  }


  ngOnInit(): void {
    this.getAccountForUser();
  }

  deleteAccount(accountId) {
    this.accountService.deleteAccountById(this.userId, accountId).subscribe(response => {
      this.getAccountForUser();
    })
  }

  getAccountForUser() {
    this.accountService.getAccountsForUser(this.tokenStorage.getUserID()).subscribe(response => {
      this.accounts = response.body;
    })
  }

}
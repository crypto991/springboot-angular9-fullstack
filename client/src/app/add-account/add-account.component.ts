import { AccountService } from './../_services/account.service';
import { Customer } from './../classes/customer';
import { TokenStorageService } from './../_services/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Account } from '../classes/account';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit {

  account = {} as Account;
  customer = {} as Customer;


  accountId: string;

  constructor(private accountService: AccountService,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private route: ActivatedRoute) {
    this.account = new Account();
  }

  ngOnInit(): void {
    this.accountId = this.route.snapshot.params['accountId'];

    console.log(this.accountId);

    if (this.accountId != undefined) {
      this.accountService.getAccountById(this.accountId)
        .subscribe(
          data => {
            this.account = data.body;
            this.customer = data.body.customer;
            console.log(this.account)
          }
        )
    }
  }


  onSubmit() {
    this.account.customer = this.customer;

    if (this.accountId == undefined) {
      this.accountService.addAccount(this.account, this.tokenStorage.getUserID()).subscribe(
        data => {

          this.gotoAccountList()
        })
    }else {
      this.accountService.updateAccount(this.accountId, this.account).subscribe(result => this.gotoAccountList());
    }

  }

  gotoAccountList() {
    this.router.navigate(['/list-user-accounts']);
  }


}

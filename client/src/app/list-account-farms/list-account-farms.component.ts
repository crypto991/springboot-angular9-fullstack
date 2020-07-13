import { FarmService } from './../_services/farm.service';
import { Farm } from './../classes/farm';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-list-account-farms',
  templateUrl: './list-account-farms.component.html',
  styleUrls: ['./list-account-farms.component.css']
})
export class ListAccountFarmsComponent implements OnInit {

  farms: Farm[];
  accountId: string;


  constructor(private farmService : FarmService,
              private route: ActivatedRoute,
              private router: Router) {

   }

  ngOnInit(): void {
    this.getAllFarmsForAccount();
  }

  getAllFarmsForAccount() {
    this.accountId = this.route.snapshot.params['accountId'];

    this.farmService.getFarmsForAccount(this.accountId).subscribe(response => {
      this.farms = response.body;
    })
  }

  addFarm() {
    this.router.navigate(['/addFarm', this.accountId]);
  }

  editFarm(farmId) {
    this.router.navigate(['/editFarm', this.accountId, farmId]);

  }

  deleteFarm(farmId) {
    this.farmService.deleteFarmById(this.accountId, farmId).subscribe(response => {
      this.farms = response.body;
    })
  }
}

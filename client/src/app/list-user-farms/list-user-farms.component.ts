import { FarmService } from './../_services/farm.service';
import { Farm } from './../classes/farm';
import { TokenStorageService } from './../_services/token-storage.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-user-farms',
  templateUrl: './list-user-farms.component.html',
  styleUrls: ['./list-user-farms.component.css']
})
export class ListUserFarmsComponent implements OnInit {

  farms: Farm[];

  constructor(private farmService : FarmService,
              private tokenStorage: TokenStorageService) {

   }


  ngOnInit(): void {
    this.farmService.getFarmsForUser(this.tokenStorage.getUserID()).subscribe(response => {
      this.farms = response.body;
    })

  }

}

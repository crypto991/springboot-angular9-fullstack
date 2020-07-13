import { FarmService } from './../_services/farm.service';
import { Farm } from './../classes/farm';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-farm',
  templateUrl: './add-farm.component.html',
  styleUrls: ['./add-farm.component.css']
})
export class AddFarmComponent implements OnInit {

  farm = {} as Farm;
  accountId: string;
  farmId: string;


  constructor(private farmService: FarmService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.farmId = this.route.snapshot.params['farmId'];
    this.accountId = this.route.snapshot.params['accountId'];
    console.log(this.farmId);
    console.log(this.accountId);

    if (this.farmId != undefined) {
      this.farmService.getFarmById(this.farmId)
        .subscribe(
          data => {
            this.farm = data.body;
            console.log(this.farm)
          }
        )
    }
  }


  onSubmit() {
    if (this.farmId == undefined) {
      this.farmService.addFarm(this.farm, this.accountId).subscribe(
        data => {
          this.gotoFarmList()
        })
    } else {
      this.farmService.editFarm(this.farm, this.farmId).subscribe(
        data => {
          this.gotoFarmList()
        })
    }


  }

  gotoFarmList() {
    this.router.navigate(['/list-account-farms', this.accountId]);
  }

}

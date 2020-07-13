import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFarmComponent } from './add-farm.component';

describe('AddFarmComponent', () => {
  let component: AddFarmComponent;
  let fixture: ComponentFixture<AddFarmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFarmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFarmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

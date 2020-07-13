import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAccountFarmsComponent } from './list-account-farms.component';

describe('ListAccountFarmsComponent', () => {
  let component: ListAccountFarmsComponent;
  let fixture: ComponentFixture<ListAccountFarmsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListAccountFarmsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListAccountFarmsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListUserFarmsComponent } from './list-user-farms.component';

describe('ListUserFarmsComponent', () => {
  let component: ListUserFarmsComponent;
  let fixture: ComponentFixture<ListUserFarmsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListUserFarmsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListUserFarmsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

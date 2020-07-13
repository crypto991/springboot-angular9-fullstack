import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListUserAccountsComponent } from './list-user-accounts.component';

describe('ListUserAccountsComponent', () => {
  let component: ListUserAccountsComponent;
  let fixture: ComponentFixture<ListUserAccountsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListUserAccountsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListUserAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

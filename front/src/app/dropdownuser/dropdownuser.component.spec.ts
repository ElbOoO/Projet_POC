import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DropdownuserComponent } from './dropdownuser.component';

describe('DropdownuserComponent', () => {
  let component: DropdownuserComponent;
  let fixture: ComponentFixture<DropdownuserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DropdownuserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DropdownuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

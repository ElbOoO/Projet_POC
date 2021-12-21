import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Color, Colors } from 'angular-bootstrap-md';
import { ColdObservable } from 'rxjs/internal/testing/ColdObservable';

import { FormManagerComponent } from './form-manager.component';

describe('FormManagerComponent', () => {
  let component: FormManagerComponent;
  let fixture: ComponentFixture<FormManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormManagerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  

});

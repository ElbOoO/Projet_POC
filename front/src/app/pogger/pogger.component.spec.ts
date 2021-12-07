import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoggerComponent } from './pogger.component';

describe('PoggerComponent', () => {
  let component: PoggerComponent;
  let fixture: ComponentFixture<PoggerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoggerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoggerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

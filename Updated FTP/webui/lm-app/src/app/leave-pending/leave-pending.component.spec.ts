import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Http,HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { LeaveDetails } from '../LeaveDetails';
import { LeaveDetailsService } from '../leave-details.service';
import { LeavePendingComponent } from './leave-pending.component';

class MockLeaveDetailsService {
  getLeavePending(empId): Promise<LeaveDetails[]> {
    console.log('Mock getLeavePending called');
    return Promise.resolve([new LeaveDetails(1, 1000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
    "SICK", "2019-06-21", "APP")]);
  }
}
describe('LeaveHistoryComponent', () => {
  let component: LeavePendingComponent;
  let fixture: ComponentFixture<LeavePendingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeavePendingComponent ],imports:[RouterTestingModule,FormsModule,HttpModule]
    }).overrideComponent(LeavePendingComponent, {
      set: {
        providers: [
          {provide: LeaveDetailsService, useClass: MockLeaveDetailsService }
        ]
      }
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeavePendingComponent);
    component = fixture.componentInstance;
    // fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
  it('should render one pending record', async(() => {
    const fixture = TestBed.createComponent(LeavePendingComponent);
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      expect(compiled.querySelectorAll('.leavePending tr').length).toBe(1);
    });
  }));
});

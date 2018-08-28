import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Http,HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { LeaveHistoryComponent } from './leave-history.component';
import { Employee } from '../employee';
import { LeaveDetails } from '../LeaveDetails';
import { LeaveDetailsService } from '../leave-details.service';

class MockLeaveDetailsService {
  getLeaveHistory(empId): Promise<LeaveDetails[]> {
    console.log('Mock getLeaveHistory called');
    return Promise.resolve([new LeaveDetails(1, 1000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
    "SICK", "2019-06-21", "APP")]);
  }
}
describe('LeaveHistoryComponent', () => {
  let component: LeaveHistoryComponent;
  let fixture: ComponentFixture<LeaveHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeaveHistoryComponent ],imports:[RouterTestingModule,FormsModule,HttpModule]
    }).overrideComponent(LeaveHistoryComponent, {
      set: {
        providers: [
          {provide: LeaveDetailsService, useClass: MockLeaveDetailsService }
        ]
      }
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeaveHistoryComponent);
    component = fixture.componentInstance;
    // fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
  it('should render one history record', async(() => {
    const fixture = TestBed.createComponent(LeaveHistoryComponent);
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      expect(compiled.querySelectorAll('.leaveHistory tr').length).toBe(1);
    });
  }));
});

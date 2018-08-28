import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Http,HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ManagerApprovalComponent } from './manager-approval.component';
import { LeaveDetails } from '../LeaveDetails';
import { LeaveDetailsService } from '../leave-details.service';
import { EmployeeService } from '../employee.service';
class MockLeaveDetailsService {
  getManagerPending(empId): Promise<LeaveDetails[]> {
    console.log('Mock getLeavePending called');
    return Promise.resolve([new LeaveDetails(1, 1000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
    "SICK", "2019-06-21", "APP")]);
  }
}
describe('ManagerApprovalComponent', () => {
  let component: ManagerApprovalComponent;
  let fixture: ComponentFixture<ManagerApprovalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerApprovalComponent ],imports:[RouterTestingModule,FormsModule,HttpModule]
    }).overrideComponent(ManagerApprovalComponent, {
      set: {
        providers: [EmployeeService,
          {provide: LeaveDetailsService, useClass: MockLeaveDetailsService }
        ]
      }
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerApprovalComponent);
    component = fixture.componentInstance;
    // fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
  it('should render manager pending record', async(() => {
    const fixture = TestBed.createComponent(ManagerApprovalComponent);
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      expect(component.getManagerPending.length).toBe(1);
    });
  }));
});



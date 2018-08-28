import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Http,HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ApproveDenyComponent } from './approve-deny.component';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { LeaveDetailsService } from '../leave-details.service';
import { LeaveDetails } from '../LeaveDetails';

class MockEmployeeService {
  getEmployeeDetails(empId): Promise<Employee> {
    console.log('Mock getEmployees called');
    empId = 1000;
    return Promise.resolve(new Employee(empId,"sai","2019-03-24",1000,9912140507,"IT","kethu@gmail.com",30));
  }
}
class MockLeaveDetailsService {
  getLeaveId(leaveId): Promise<LeaveDetails> {
    console.log('Mock getLeavePending called');
    leaveId=1;
    return Promise.resolve(new LeaveDetails(leaveId, 1000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
    "SICK", "2019-06-21", "APP"));
  }
}

describe('ApproveDenyComponent', () => {
  let component: ApproveDenyComponent;
  let fixture: ComponentFixture<ApproveDenyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApproveDenyComponent ],imports:[RouterTestingModule,FormsModule,HttpModule], providers: [EmployeeService]
    })
    .overrideComponent(ApproveDenyComponent, {
      set: {
        providers: [{provide: LeaveDetailsService, useClass: MockLeaveDetailsService },
          {provide: EmployeeService, useClass: MockEmployeeService }
        ]
      }
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveDenyComponent);
    component = fixture.componentInstance;
    // fixture.detectChanges();
  });
  it('should compare employee Details', async(() => {
    const fixture = TestBed.createComponent(ApproveDenyComponent);
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      // fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      expect(component.getEmployeeDetails.length).toBe(1);
    });
  }));
  it('should compare leave Details', async(() => {
    const fixture = TestBed.createComponent(ApproveDenyComponent);
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      // fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      expect(component.getLeaveId.length).toBe(1);
    });
  }));
  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

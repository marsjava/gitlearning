import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Http,HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { LeaveApplyComponent } from './leave-apply.component';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { LeaveDetailsService } from '../leave-details.service';
class MockEmployeeService {
  getEmployeeDetails(empId): Promise<Employee> {
    console.log('Mock getEmployees called');
    empId = 1000;
    return Promise.resolve(new Employee(empId,"sai","2019-03-24",1000,9912140507,"IT","kethu@gmail.com",30));
  }
  // setApply(employeeId, startDate, endDate, leaveReason, selectedOption){
  //   return to.subscribe(setApply());
  // }
}

describe('LeaveApplyComponent', () => {
  let component: LeaveApplyComponent;
  let fixture: ComponentFixture<LeaveApplyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeaveApplyComponent ],imports:[RouterTestingModule,FormsModule,HttpModule]
    }).overrideComponent( LeaveApplyComponent, {
      set: {
        providers: [LeaveDetailsService,
          {provide: EmployeeService, useClass: MockEmployeeService }
        ]
      }
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeaveApplyComponent);
    component = fixture.componentInstance;
    // fixture.detectChanges();
  });
it('should compare employee Details', async(() => {
    const fixture = TestBed.createComponent(LeaveApplyComponent);
    // fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      expect(component.getEmployeeDetails.length).toBe(1);
    });
  }));

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
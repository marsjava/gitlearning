import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Http,HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { MydetailsComponent } from './mydetails.component';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
class MockEmployeeService {
  getEmployeeDetails(empId): Promise<Employee> {
    console.log('Mock getEmployees called');
    empId = 1000;
    return Promise.resolve(new Employee(empId,"sai","2019-03-24",1000,9912140507,"IT","kethu@gmail.com",30));
  }
}
describe('MydetailsComponent', () => {
  let component: MydetailsComponent;
  let fixture: ComponentFixture<MydetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MydetailsComponent ], imports:[HttpModule,FormsModule,RouterTestingModule]
    })
    .overrideComponent( MydetailsComponent, {
      set: {
        providers: [
          {provide: EmployeeService, useClass: MockEmployeeService }
        ]
      }
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MydetailsComponent);
    component = fixture.componentInstance;
    // fixture.detectChanges();
  });
  it('should compare employee Details', async(() => {
    const fixture = TestBed.createComponent(MydetailsComponent);
    // fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      // let expectedDetails: Employee;
      // expectedDetails = new Employee(1000,"sai","2019-03-24",1000,9912140507,"IT","kethu@gmail.com",30) as Employee;
      // expect(component.getEmployeeDetails).toBe(expectedDetails);
      expect(component.getEmployeeDetails.length).toBe(1);
      // expect(compiled.querySelectorAll('.empId')).toBe(1000);
    });
  }));

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

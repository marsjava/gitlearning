import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Http,HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { MymanagerComponent } from './mymanager.component';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
class MockEmployeeService {
  getEmployeeDetails(empId): Promise<Employee> {
    console.log('Mock getEmployees called');
    empId = 1000;
    return Promise.resolve(new Employee(empId,"sai","2019-03-24",1000,9912140507,"IT","kethu@gmail.com",30));
  }
}
describe('MymanagerComponent', () => {
  let component: MymanagerComponent;
  let fixture: ComponentFixture<MymanagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MymanagerComponent ],imports:[RouterTestingModule,FormsModule,HttpModule]
    }).overrideComponent( MymanagerComponent, {
      set: {
        providers: [
          {provide: EmployeeService, useClass: MockEmployeeService }
        ]
      }
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MymanagerComponent);
    component = fixture.componentInstance;
    // fixture.detectChanges();
  });

  it('should compare employee Details', async(() => {
    const fixture = TestBed.createComponent(MymanagerComponent);
    // fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      expect(component.getEmployeeDetails).toBe(1);
    });
  }));

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

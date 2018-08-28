import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { Http,HttpModule } from '@angular/http';
import { DashboardComponent } from './dashboard.component';
import {RouterTestingModule} from '@angular/router/testing'
import { ReactiveFormsModule } from '@angular/forms';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';

class MockEmployeeService {
  getEmployeeDetails(empId): Promise<Employee> {
    console.log('Mock getEmployees called');
    empId = 1000;
    return Promise.resolve(new Employee(empId,"sai","2019-03-24",1000,9912140507,"IT","kethu@gmail.com",30));
  }
}
describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashboardComponent ],imports: [ReactiveFormsModule,RouterTestingModule,HttpModule ], providers: [EmployeeService]
    }).overrideComponent(DashboardComponent, {
      set: {
        providers: [
          {provide: EmployeeService, useClass: MockEmployeeService }
        ]
      }
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    // fixture.detectChanges();
  });

  it('should compare employee Details', async(() => {
    const fixture = TestBed.createComponent(DashboardComponent);
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      expect(component.getEmployeeDetails.length).toBe(1);
    });
  }));

  // it('should compare employee Details', async(() => {
  //   const fixture = TestBed.createComponent(DashboardComponent);
  //   fixture.detectChanges();
  //   fixture.whenStable().then(() => {
  //     fixture.detectChanges();
  //     const compiled = fixture.debugElement.nativeElement;
  //     expect(component.getEmployeeDetails(1000)).toEqual(new Employee(1000,"sai","2019-03-24",1000,9912140507,"IT","kethu@gmail.com",30))
  //   });
  // }));
  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

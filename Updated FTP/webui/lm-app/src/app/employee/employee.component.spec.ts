import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeComponent } from './employee.component';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { Http, HttpModule } from '@angular/http';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

class MockEmployeeService {
  getEmployees(): Promise<Employee[]> {
    console.log('Mock getEmployees called');
    return Promise.resolve([new Employee(1000,"sai","2019-03-24",1000,9912140507,"IT","kethu@gmail.com",30),
      new Employee(2000,"kumar","2019-03-24",1000,9912140508,"IT","kethu@gmail.com",30)]);
  }
}
describe('EmployeeComponent', () => {
  let component: EmployeeComponent;
  let fixture: ComponentFixture<EmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeComponent ],imports:[RouterTestingModule,FormsModule,HttpModule]
    }).overrideComponent(EmployeeComponent, {
      set: {
        providers: [
          {provide: EmployeeService, useClass: MockEmployeeService }
        ]
      }
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeComponent);
    component = fixture.componentInstance;
    // fixture.detectChanges();
  });
  it('should render one employee record', async(() => {
    const fixture = TestBed.createComponent(EmployeeComponent);
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const compiled = fixture.debugElement.nativeElement;
      expect(compiled.querySelectorAll('.employees tr').length).toBe(2);
    });
  }));

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

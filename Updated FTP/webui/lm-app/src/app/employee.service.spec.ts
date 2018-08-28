// import { TestBed, inject } from '@angular/core/testing';
// import { Http,HttpModule } from '@angular/http';
// import { EmployeeService } from './employee.service';
// import { Observable } from 'rxjs/Observable';
// import { EmployeeComponent } from './employee/employee.component';
// import { Employee } from './employee';

// describe('EmployeeService', () => {
//   let service: EmployeeService;
//   let employee : Employee;
//   beforeEach(() => {
    
//     TestBed.configureTestingModule({
//       providers: [EmployeeService], imports:[HttpModule]
//     });
//   });
// });
import { TestBed, inject } from '@angular/core/testing';
import { Http,HttpModule } from '@angular/http';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { async, ComponentFixture} from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { EmployeeService } from './employee.service';
import { Employee } from './employee';

describe('Employee Details Service', () => {
  describe ('Employee Details Service(with spies)', () => {
    let httpClientSpy: { get: jasmine.Spy };
    let employeeService: EmployeeService;
  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    employeeService = new EmployeeService(<any> httpClientSpy);
    TestBed.configureTestingModule({
      providers: [EmployeeService], imports:[HttpModule]
    });
  });

  it('should be created', inject([EmployeeService], (service: EmployeeService) => {
    expect(service).toBeTruthy();
  }));
  });
  describe('LeaveHistory', () => {
    let httpClient: HttpClient;
    let httpTestingController: HttpTestingController;
    let leaveDetailsService: EmployeeService;
  
    beforeEach(() => {
      TestBed.configureTestingModule({
        // Import the HttpClient mocking services
        imports: [ HttpClientTestingModule, HttpModule ],
        // Provide the service-under-test
        providers: [ EmployeeService ]
      });
  
      // Inject the http, test controller, and service-under-test
      // as they will be referenced by each test.
      httpClient = TestBed.get(HttpClient);
      httpTestingController = TestBed.get(HttpTestingController);
      leaveDetailsService= TestBed.get(EmployeeService);
    });
  
    afterEach(() => {
      // After every test, assert that there are no more pending requests.
      httpTestingController.verify();
    });
  
  
      it('should compare EmployeeDetails by Id ', () => {
        const empId = 1000;
        let expectedDetails: Employee;
        expectedDetails = new Employee(empId,"sai","2019-03-24",1000,9912140507,"IT","kethu@gmail.com",30) as Employee;
         leaveDetailsService.getEmployeeDetails(empId).then(
          data => expect(data).toEqual(expectedDetails, 'should return the employeeDetails'),
          fail
        )});
        
    //     // HeroService should have made one request to PUT hero
    //     const req = httpTestingController.expectOne(heroService.heroesUrl);
    //     expect(req.request.method).toEqual('PUT');
    //     expect(req.request.body).toEqual(updateHero);
  
    //     // Expect server to return the hero after PUT
    //     const expectedResponse = new HttpResponse(
    //       { status: 200, statusText: 'OK', body: updateHero });
    //     req.event(expectedResponse);
    //   });
  
    //   it('should turn 404 error into user-facing error', () => {
    //     const msg = 'Deliberate 404';
    //     const updateHero: Hero = { id: 1, name: 'A' };
    //     heroService.updateHero(updateHero).subscribe(
    //       heroes => fail('expected to fail'),
    //       error => expect(error.message).toContain(msg)
    //     );
  
    //     const req = httpTestingController.expectOne(heroService.heroesUrl);
  
    //     // respond with a 404 and the error message in the body
    //     req.flush(msg, {status: 404, statusText: 'Not Found'});
    //   });
  
    //   it('should turn network error into user-facing error', () => {
    //     const emsg = 'simulated network error';
  
    //     const updateHero: Hero = { id: 1, name: 'A' };
    //     heroService.updateHero(updateHero).subscribe(
    //       heroes => fail('expected to fail'),
    //       error => expect(error.message).toContain(emsg)
    //     );
  
    //     const req = httpTestingController.expectOne(heroService.heroesUrl);
  
    //     // Create mock ErrorEvent, raised when something goes wrong at the network level.
    //     // Connection timeout, DNS error, offline, etc
    //     const errorEvent = new ErrorEvent('so sad', {
    //       message: emsg,
    //       // The rest of this is optional and not used.
    //       // Just showing that you could provide this too.
    //       filename: 'HeroService.ts',
    //       lineno: 42,
    //       colno: 21
    //     });
  
    //     // Respond with mock error
    //     req.error(errorEvent);
    //   });
    // });
  
    // // TODO: test other HeroService methods
  
});
  });




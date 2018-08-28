import { TestBed, inject } from '@angular/core/testing';
import { Http,HttpModule } from '@angular/http';
import { LeaveDetailsService } from './leave-details.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { async, ComponentFixture} from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { LeaveDetails } from './LeaveDetails';
describe('LeaveDetailsService', () => {
  describe ('LeaveDetailsService (with spies)', () => {
    let httpClientSpy: { get: jasmine.Spy };
    let leaveDetailsService: LeaveDetailsService;
  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    leaveDetailsService = new LeaveDetailsService(<any> httpClientSpy);
    TestBed.configureTestingModule({
      providers: [LeaveDetailsService], imports:[HttpModule]
    });
  });

  it('should be created', inject([LeaveDetailsService], (service: LeaveDetailsService) => {
    expect(service).toBeTruthy();
  }));
  });
  describe('LeaveDetails Service check', () => {
    let httpClient: HttpClient;
    let httpTestingController: HttpTestingController;
    let leaveDetailsService: LeaveDetailsService;
  
    beforeEach(() => {
      TestBed.configureTestingModule({
        // Import the HttpClient mocking services
        imports: [ HttpClientTestingModule, HttpModule ],
        // Provide the service-under-test
        providers: [ LeaveDetailsService ]
      });
  
      // Inject the http, test controller, and service-under-test
      // as they will be referenced by each test.
      httpClient = TestBed.get(HttpClient);
      httpTestingController = TestBed.get(HttpTestingController);
      leaveDetailsService= TestBed.get(LeaveDetailsService);
    });
  
    afterEach(() => {
      // After every test, assert that there are no more pending requests.
      httpTestingController.verify();
    });
  
      

    // describe('LeaveHIstory method', () => {
    //   // Expecting the query form of URL so should not 404 when id not found
    //   const makeUrl = (id: number) => `${leaveDetailsService}http://localhost:8080/ftp47/api/leave/?id=${id}/history`;
    //   let expectedDetails: LeaveDetails[];
    //   this.empId = 1000;
    //   beforeEach(() => {
    //     leaveDetailsService = TestBed.get(LeaveDetailsService);
    //     expectedDetails = [
    //      new LeaveDetails(1, 1000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
    //      "SICK", "2019-06-21", "APP"), new LeaveDetails(2, 2000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
    //      "SICK", "2019-06-21", "APP")
    //      ] as LeaveDetails[];
    //   });
      it('should compare leaveHistory ', () => {
        const empId = 1000;
        let expectedDetails: LeaveDetails[];
        expectedDetails = [
          new LeaveDetails(1, 1000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
          "SICK", "2019-06-21", "APP"), new LeaveDetails(2, 2000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
          "SICK", "2019-06-21", "testing")
          ] as LeaveDetails[];
         leaveDetailsService.getLeaveHistory(empId).then(
          data => expect(data).toEqual(expectedDetails, 'should return the hero'),
          fail
        )});

        it('should compare leavePending ', () => {
          const empId = 1000;
          let expectedDetails: LeaveDetails[];
          expectedDetails = [
            new LeaveDetails(1, 1000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
            "SICK", "2019-06-21", "APP"), new LeaveDetails(2, 2000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
            "SICK", "2019-06-21", "testing")
            ] as LeaveDetails[];
           leaveDetailsService.getLeavePending(empId).then(
            data => expect(data).toEqual(expectedDetails, 'should return the history mentioned'),
            fail
          )});

          it('should compare leavePending ', () => {
            const empId = 1000;
            let expectedDetails: LeaveDetails[];
            expectedDetails = [
              new LeaveDetails(1, 1000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
              "SICK", "2019-06-21", "APP"), new LeaveDetails(2, 2000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
              "SICK", "2019-06-21", "testing")
              ] as LeaveDetails[];
             leaveDetailsService.getLeavePending(empId).then(
              data => expect(data).toEqual(expectedDetails, 'should return the pending mentioned'),
              fail
            )});
            it('should compare managerLeavePending ', () => {
              const empId = 1000;
              let expectedDetails: LeaveDetails[];
              expectedDetails = [
                new LeaveDetails(2, 2000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
                "SICK", "2019-06-21", "APP"), new LeaveDetails(2, 2000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
                "SICK", "2019-06-21", "testing")
                ] as LeaveDetails[];
               leaveDetailsService.getManagerPending(empId).then(
                data => expect(data).toEqual(expectedDetails, 'should return the managerPending'),
                fail
              )});
              it('should compare leaveId ', () => {
                let expectedDetails: LeaveDetails;
                const leaveId = 2;
                expectedDetails = 
                  new LeaveDetails(2, 2000, 2, "2019-06-24", "2019-06-25", "EL", "PENDING",
                  "SICK", "2019-06-21", "APP") as LeaveDetails;
                 leaveDetailsService.getLeaveId(leaveId).then(
                  data => expect(data).toEqual(expectedDetails, 'should return the LeaveId'),
                  fail
                )});

                // it('should compare apply ', () => {
                //   let leaveDetailsService: LeaveDetailsService;
                //   const empId = 1000;
                //   const startDate = "2018-06-24";
                //   const endDate = "2018-06-25";
                //   const leaveReason = "SICK";
                //   const selectedOption = "EL";
                //   this.app = leaveDetailsService.setApply(1000,"2018-08-24","2018-08-25","dsads","SL");
                //    leaveDetailsService.setApply(empId, startDate, endDate, leaveReason, selectedOption).subscribe(
                //     data => expect(data).toEqual(this.app ,'should return the details'),
                //     fail
                //   )});
  
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




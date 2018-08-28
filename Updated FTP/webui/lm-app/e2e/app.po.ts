// import { browser, by, element } from 'protractor';

// export class AppPage {
//   navigateTo() {
//     return browser.get('/ftp47/');
//   }

//   getParagraphText() {
//     return element(by.css('app-root h1')).getText();
//   }

//   getRows() {
//     const tbody = element(by.tagName('tbody'));
//     return tbody.all(by.tagName('tr'));
//   }

// }


import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }

  getLeaveButton(){
    return element(by.css('[routerLink="/table"]'));
  }
  getLeaveText(){
    return element(by.css('app-employee h1')).getText();
  }
  getRows() {
    const tbody = element(by.tagName('tbody'));
    return tbody.all(by.tagName('tr'));
    }
}
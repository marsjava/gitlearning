import { AppPage } from './app.po';

import { browser, by, element } from 'protractor';

describe('lm-app App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Leave Management System');
  });

  it('should display homepage message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Leave Management System');
  });

  it('should route to table page', () => {
    page.navigateTo();
    page.getLeaveButton().click();
    expect(page.getLeaveText()).toEqual('Employee Login');
  it('should display 5 IDs', () => {
    const expected = new Set();
    expected.add('1000');
    expected.add('2000');
    expected.add('2001');
    expected.add('3000');
    expected.add('3001');
    page.navigateTo();
    const rows = page.getRows();
    expect(rows.count()).toBe(5);
    rows.each(function(row, index) {
      row.all(by.tagName('td')).first().getText().then(t => {
        expected.delete(t);
      });
    }).then(() => {
      expect(expected.size).toBe(0);
    });
  });
});
});
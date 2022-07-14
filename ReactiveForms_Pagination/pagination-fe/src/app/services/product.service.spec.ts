import { TestBed } from '@angular/core/testing';

import { Productservice } from './product.service';

describe('ProductserviceService', () => {
  let service: Productservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Productservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

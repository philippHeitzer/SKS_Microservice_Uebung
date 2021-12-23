import { TestBed } from '@angular/core/testing';

import { BlogStoreServiceService } from './blog-store-service.service';

describe('BlogStoreServiceService', () => {
  let service: BlogStoreServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BlogStoreServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

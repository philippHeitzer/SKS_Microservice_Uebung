import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Blog } from '../shared/Blog';
import { BlogStoreServiceService } from '../shared/blog-store-service.service';

@Component({
  selector: 'app-blog-details',
  templateUrl: './blog-details.component.html',
  styleUrls: ['./blog-details.component.css']
})
export class BlogDetailsComponent implements OnInit {

  blog? : Blog;

  constructor(private bs: BlogStoreServiceService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    const params = this.route.snapshot.paramMap;
    this.bs.getSingle(params.get('id') || '')
      .subscribe(b => this.blog = b);
  }

}

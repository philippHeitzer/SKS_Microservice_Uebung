import { Component, OnInit } from '@angular/core';
import { BlogStoreServiceService } from '../shared/blog-store-service.service';
import { Observable } from 'rxjs';
import { Blog } from '../shared/Blog';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {

  blogs$: Observable<Blog[]>

  constructor(private bs: BlogStoreServiceService) {
    this.blogs$ = this.bs.getAllBlogs();
   }

  ngOnInit(): void {

  }

}

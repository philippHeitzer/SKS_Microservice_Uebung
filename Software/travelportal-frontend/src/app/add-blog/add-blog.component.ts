import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup ,Validators} from '@angular/forms';
import { Author, Blog, Attraction } from '../shared/Blog';
import { BlogStoreServiceService } from '../shared/blog-store-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-blog',
  templateUrl: './add-blog.component.html',
  styleUrls: ['./add-blog.component.css']
})
export class AddBlogComponent implements OnInit {

  blogForm : FormGroup

  error = false;

  constructor(private fb: FormBuilder,
    private bs: BlogStoreServiceService,
    private route: ActivatedRoute,
    private router: Router) {
    this.blogForm = this.fb.group({
      title: ['',Validators.required],
      author: ['',Validators.required],
      blogText: ['',Validators.required],
      attraction: ['',Validators.required]
    })
  }

  ngOnInit(): void {
    this.error= false;
  }

  submit(){
    const value = this.blogForm.value;

    const firstname = value.author.split(' ')[0];
    const lastname = value.author.split(' ')[1];

    const newAuthor : Author = {
      id: 0,
      firstname: firstname,
      lastname : lastname
    };

    const newAttraction : Attraction = {
     id: 0,
     name: value.attraction,
     description: null
    }

    const newBlog : Blog = {
      id: 0,
      title: value.title,
      blogText: value.blogText,
      author: newAuthor,
      attraction: newAttraction
    }

    this.bs.createBlog(newBlog).subscribe(() => {
      this.router.navigate(['blogs']);
      }
    );

    console.log(newBlog);
  }

}

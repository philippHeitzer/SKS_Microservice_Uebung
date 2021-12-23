import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AttractionStats } from '../shared/AttractionStats';
import { BlogStoreServiceService } from '../shared/blog-store-service.service';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  attractions$ : Observable<AttractionStats[]>
  listSize : number

  constructor(private bs: BlogStoreServiceService) { }

  ngOnInit(): void {
    this.listSize = 10;
    this.attractions$ = this.bs.getStats(this.listSize.toString())
  }

  getAttractions()
  {
    this.attractions$ = this.bs.getStats(this.listSize.toString())
  }
}

import {
  Component,
  OnInit
}                         from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-error-4xx',
  templateUrl: './error-4xx.component.html'
})
export class Error4xxComponent implements OnInit {
  errorCode: string = '404';
  errorMessage: string = 'Not Fund';

  constructor(
    private route: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.errorCode = params['code'] || '404';
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-users-account-list',
    templateUrl: 'list.component.html'
})
export class ListComponent implements OnInit {


    constructor(private router: Router) {}


    ngOnInit() {
    }

}

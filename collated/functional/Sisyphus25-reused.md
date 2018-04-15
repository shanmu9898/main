# Sisyphus25-reused
###### /resources/view/LightTheme.css
``` css
Reused from DarkTheme.css with modifications */

.background {
    -fx-background-color: #ffffff;
    background-color: #ffffff; /* Used in the default.html file */
}

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: #555555;
    -fx-opacity: 0.9;
}

.app-title {
    -fx-text-fill: brown;
    -fx-font-family: "Franklin Gothic Medium";
    -fx-font-weight: bold;
    -fx-font-size: 36px
}

.cell_index_label {
    -fx-font-size: 20px;
    -fx-font-family: "Franklin Gothic Medium";
}

.cell_circle {
    -fx-stroke: brown;
}

.studentList {
    -fx-background-color: derive(#ffd0d0, 20%);
    -fx-border-radius: 0 10 0 0;
    -fx-background-radius: 0 10 0 0;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

.tab-pane {
    -fx-padding: 0 0 0 1;
}

.tab-pane .tab-header-area {
    -fx-padding: 0 0 0 0;
    -fx-min-height: 0;
    -fx-max-height: 0;
}

.table-view {
    -fx-base: #1d1d1d;
    -fx-control-inner-background: #ffffff;
    -fx-background-color: #ffffff;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color:
            transparent
            transparent
            derive(-fx-base, 80%)
            transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: black;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal .split-pane-divider {
    -fx-background-color: derive(#ffffff, 20%);
    -fx-border-color: transparent transparent transparent tomato;
}

.split-pane {
    -fx-border-radius: 1;
    -fx-border-width: 1;
    -fx-background-color: derive(#ffffff, 20%);
}

.list-view {
    -fx-background-insets: 0;
    -fx-padding: 0;
    -fx-background-color: derive(#ffffff, 20%);

}

.list-cell {
    -fx-label-padding: 0 0 0 0;
    -fx-graphic-text-gap : 0;
    -fx-background-radius: 10 10 10 10;
    -fx-border-radius: 10 10 10 10;
    -fx-padding: 10px;
    -fx-background-insets: 3px, 3px;
    -fx-background-color: transparent
}

.list-cell:filled:even {
    -fx-background-color: #ffd0d0;
}

.list-cell:filled:odd {
    -fx-background-color: #ffd0d0;
}

.list-cell:filled:selected {
    -fx-background-color: #ffc2c2;
}

.list-cell:filled:selected #cardPane {
    -fx-border-color: #ffc2c2;
    -fx-border-width: 1;
}

.list-cell .label {
    -fx-text-fill: brown;
}

.cell_big_label {
    -fx-font-family: "Segoe UI Semibold";
    -fx-font-size: 16px;
    -fx-text-fill: #ffd0d0;
}

.cell_small_label {
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13px;
    -fx-text-fill: #ffd0d0;
}

.anchor-pane {
    -fx-background-color: derive(#ffffff, 20%);
}

.pane-with-border {
    -fx-background-color: derive(#ffffff, 20%);
    -fx-border-color: transparent;
    -fx-border-top-width: 1px;
}

.status-bar {
    -fx-background-color: derive(#ffd0d0, 20%);
    -fx-text-fill: white;
}

.result-display {
    -fx-background-color: transparent;
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13pt;
    -fx-text-fill: brown;
}

.result-display .label {
    -fx-text-fill: white !important;
}

.status-bar .label {
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: brown;
}

.status-bar-with-border {
    -fx-background-color: derive(#ffd0d0, 30%);
    -fx-border-color: derive(#ffd0d0, 25%);
    -fx-border-width: 1px;
}

.status-bar-with-border .label {
    -fx-text-fill: brown;
}

.grid-pane {
    -fx-background-color: derive(#ffd0d0, 30%);
    -fx-border-color: derive(#ffd0d0, 30%);
    -fx-border-width: 1px;
}

.grid-pane .anchor-pane {
    -fx-background-color: derive(#ffd0d0, 30%);
}

.calendar-panel .button {
    -fx-text-fill: #000000;
}

.calendar-panel {
    -fx-background-color: #ffffff;
    background-color: #ffffff;
}

.calendar-panel .content {
    -fx-border-color: transparent transparent tomato transparent;
}

.calendar-panel .header {
    -fx-border-color: tomato transparent transparent transparent;
}

.context-menu {
    -fx-background-color: derive(#ffd0d0, 50%);
}

.context-menu .label {
    -fx-text-fill: brown;
}

.menu-bar {
    -fx-background-color: derive(#ffd0d0, 20%);
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: brown;
    -fx-opacity: 0.9;
}

.menu .left-container {
    -fx-background-color: black;
}

/*
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
    -fx-background-color: white;
    -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color, 30%);
}

.dialog-pane {
    -fx-background-color: #ffffff;
}

.dialog-pane > *.button-bar > *.container {
    -fx-background-color: #ffffff;
}

.dialog-pane > *.label.content {
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-text-fill: brown;
}

.dialog-pane:header *.header-panel {
    -fx-background-color: derive(#ffd0d0, 25%);
}

.dialog-pane:header *.header-panel *.label {
    -fx-font-size: 18px;
    -fx-font-style: italic;
    -fx-fill: brown;
    -fx-text-fill: brown;
}

.scroll-bar {
    -fx-background-color: derive(#ffd0d0, 20%);
    -fx-border-radius: 20px;
    -fx-background-radius: 20px;

}

.scroll-bar .thumb {
    -fx-background-color: derive(tomato, 20%);

}

.scroll-bar .increment-button, .scroll-bar .decrement-button {
    -fx-background-color: transparent;
    -fx-rotate: 0;

}

.scroll-bar .increment-arrow {
    -fx-shape: "M 0 0 L 4 8 L 8 0 Z";
    -fx-background-color: #ffffff;
}

.scroll-bar .decrement-arrow {
    -fx-shape: "M 0 0 L 4 8 L 8 0 Z";
    -fx-background-color: #ffffff;
    -fx-rotate: -180;
}

.scroll-bar:vertical .increment-arrow, .scroll-bar:vertical .decrement-arrow {
    -fx-padding: 3 3 3 3;
}

.scroll-bar:horizontal .increment-arrow{
    -fx-rotate: -90;
    -fx-padding: 3 3 3 3;
}

.scroll-bar:horizontal .decrement-arrow {
    -fx-rotate: 90;
    -fx-padding: 3 3 3 3;
}

#cardPane {
    -fx-background-color: transparent;
    -fx-border-width: 10pt;
}

#commandTypeLabel {
    -fx-font-size: 11px;
    -fx-text-fill: #F70D1A;
}

#commandTextField {
    -fx-background-color: transparent #ffd0d0 transparent #ffd0d0;
    -fx-background-insets: 0;
    -fx-border-color: #ffd0d0 #ffd0d0 brown #ffd0d0 ;
    -fx-border-insets: 0;
    -fx-border-width: 1;
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13pt;
    -fx-text-fill: brown;
}

#filterField, #personListPanel, #personWebpage {
    -fx-effect: innershadow(gaussian, brown, 10, 0, 0, 0);
}

#resultDisplay .content {
    -fx-background-color: transparent, #ffffff, transparent, #ffffff;
    -fx-background-radius: 0;
    -fx-border-color: #ffd0d0;
}
```
###### /resources/view/DogeTheme.css
``` css
Reused from DarkTheme.css with modifications */

.root {
    -fx-background-image: url("../images/doge.jpg");
    -fx-background-repeat: repeat;
    -fx-background-position: center center;
    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0);
}

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: #555555;
    -fx-opacity: 0.9;
}

.studentList {
    -fx-background-color: derive(#efdc7f, 20%);
    -fx-border-radius: 0 10 0 0;
    -fx-background-radius: 0 10 0 0;
}


.cell_index_label {
    -fx-font-size: 20px;
    -fx-font-family: "Franklin Gothic Medium";
}

.cell_circle {
    -fx-stroke: black;
}

.app-title {
    -fx-text-fill: white;
    -fx-font-family: "Franklin Gothic Heavy";
    -fx-font-size: 40px;
}

.app-title .text {
    -fx-stroke: black;
    -fx-stroke-width: 1px;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

.tab-pane {
    -fx-padding: 0 0 0 1;
}

.tab-pane .tab-header-area {
    -fx-padding: 0 0 0 0;
    -fx-min-height: 0;
    -fx-max-height: 0;
}

.table-view {
    -fx-base: #1d1d1d;
    -fx-control-inner-background: transparent;
    -fx-background-color: transparent;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35px;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color:
            transparent
            transparent
            derive(-fx-base, 80%)
            transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: black;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal .split-pane-divider {
    -fx-background-color: transparent;
    -fx-border-color: transparent transparent transparent #635615;
}

.split-pane {
    -fx-border-radius: 1;
    -fx-border-width: 1;
    -fx-background-color: transparent;
}

.list-view {
    -fx-background-insets: 0;
    -fx-padding: 0;
    -fx-background-color: transparent;

}

.list-cell {
    -fx-label-padding: 0 0 0 0;
    -fx-graphic-text-gap : 0;
    -fx-background-radius: 10 10 10 10;
    -fx-border-radius: 10 10 10 10;
    -fx-padding: 10px;
    -fx-background-insets: 3px, 3px;
    -fx-background-color: transparent
}

.list-cell:filled:even {
    -fx-background-color: #efdc7f;
}

.list-cell:filled:odd {
    -fx-background-color: #efdc7f;
}

.list-cell:filled:selected {
    -fx-background-color: #efdc7f;
}

.list-cell:filled:selected #cardPane {
    -fx-border-color: #c1b05b;
    -fx-border-width: 1;

}

.list-cell .label {
    -fx-text-fill: black;
}

.cell_big_label {
    -fx-font-family: "Segoe UI Semibold";
    -fx-font-size: 16px;
    -fx-text-fill: #efdc7f;
}

.cell_small_label {
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13px;
    -fx-text-fill: #efdc7f;
}

.anchor-pane {
    -fx-background-color: transparent;
}

.pane-with-border {
    -fx-background-color: transparent;
    -fx-border-color: derive(#efdc7f, 10%);
    -fx-border-top-width: 1px;
}

.status-bar {
    -fx-background-color: transparent;
    -fx-text-fill: white;
}

.result-display {
    -fx-background-color: transparent;
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13pt;
    -fx-text-fill: black;
}

.result-display .label {
    -fx-text-fill: transparent !important;
}

.status-bar .label {
    -fx-font-family: "Segoe UI";
    -fx-text-fill: black;
}

.status-bar-with-border {
    -fx-background-color: transparent;
    -fx-border-color: derive(#efdc7f, 25%);
    -fx-border-width: 1px;
}

.status-bar-with-border .label {
    -fx-text-fill: black;
}

.grid-pane {
    -fx-background-color: transparent;
    -fx-border-color: derive(#efdc7f, 30%);
    -fx-border-width: 1px;
}

.grid-pane .anchor-pane {
    -fx-background-color: transparent;
}

.calendar-panel .button {
    -fx-text-fill: #000000;
}

.calendar-panel {
    -fx-background-color: transparent;
    background-color: transparent;
}

.context-menu {
    -fx-background-color: derive(#efdc7f, 50%);
}

.context-menu .label {
    -fx-text-fill: black;
}

.menu-bar {
    -fx-background-color: transparent;
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI";
    -fx-text-fill: black;
    -fx-opacity: 0.9;
}

.menu .left-container {
    -fx-background-color: black;
}

/*
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
    -fx-background-color: white;
    -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color, 30%);
}

.dialog-pane {
    -fx-background-color: transparent;
}

.dialog-pane > *.button-bar > *.container {
    -fx-background-color: #ffffff;
}

.dialog-pane > *.label.content {
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-text-fill: black;
}

.dialog-pane:header *.header-panel {
    -fx-background-color: derive(#efdc7f, 25%);
}

.dialog-pane:header *.header-panel *.label {
    -fx-font-size: 18px;
    -fx-font-style: italic;
    -fx-fill: black;
    -fx-text-fill: black;
}

.scroll-bar {
    -fx-background-color: derive(#efdc7f, 20%);
    -fx-border-radius: 20px;
    -fx-background-radius: 20px;

}

.scroll-bar .thumb {
    -fx-background-color: derive(#635615, 20%);

}

.scroll-bar .increment-button, .scroll-bar .decrement-button {
    -fx-background-color: transparent;
    -fx-rotate: 0;

}

.scroll-bar .increment-arrow {
    -fx-shape: "M 0 0 L 4 8 L 8 0 Z";
    -fx-background-color: #ffffff;
}

.scroll-bar .decrement-arrow {
    -fx-shape: "M 0 0 L 4 8 L 8 0 Z";
    -fx-background-color: #ffffff;
    -fx-rotate: -180;
}

.scroll-bar:vertical .increment-arrow, .scroll-bar:vertical .decrement-arrow {
    -fx-padding: 3 3 3 3;
}

.scroll-bar:horizontal .increment-arrow{
    -fx-rotate: -90;
    -fx-padding: 3 3 3 3;
}

.scroll-bar:horizontal .decrement-arrow {
    -fx-rotate: 90;
    -fx-padding: 3 3 3 3;
}

#cardPane {
    -fx-background-color: transparent;
    -fx-border-width: 10pt;
}

#commandTypeLabel {
    -fx-font-size: 11px;
    -fx-text-fill: #F70D1A;
}

#commandTextField {
    -fx-background-color: #efdc7f;
    -fx-background-insets: 0;
    -fx-border-color: #efdc7f #efdc7f black #efdc7f ;
    -fx-border-insets: 0;
    -fx-border-width: 1;
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13pt;
    -fx-text-fill: black;
}

#filterField, #personListPanel, #personWebpage {
    -fx-effect: innershadow(gaussian, black, 10, 0, 0, 0);
}

#resultDisplay {
    -fx-background-color: transparent;
}

#resultDisplay .scroll-pane .viewport{
    -fx-background-color: transparent;
}

#resultDisplay .content {
    -fx-background-color: #efdc7f;
    -fx-background-radius: 0;
}
```
###### /resources/view/GalaxyTheme.css
``` css
reused from DarkTheme.css with modifications */

.root {
    -fx-background-image: url("../images/galaxy.jpg");
    -fx-background-size: cover;
    -fx-background-position: center center;
    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0);
}

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: #555555;
    -fx-opacity: 0.9;
}

.studentList {
    -fx-background-color: white;
    -fx-border-radius: 0 10 0 0;
    -fx-background-radius: 0 10 0 0;
}

.app-title {
    -fx-text-fill: white;
    -fx-font-family: "Franklin Gothic Heavy";
    -fx-font-size: 40px;
}


.cell_index_label {
    -fx-font-size: 20px;
    -fx-font-family: "Franklin Gothic Medium";
}

.cell_circle {
    -fx-stroke: derive(#070f60, 50%);
}

.app-title .text {
    -fx-stroke: black;
    -fx-stroke-width: 1px;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

.tab-pane {
    -fx-padding: 0 0 0 1;
}

.tab-pane .tab-header-area {
    -fx-padding: 0 0 0 0;
    -fx-min-height: 0;
    -fx-max-height: 0;
}

.table-view {
    -fx-base: #1d1d1d;
    -fx-control-inner-background: transparent;
    -fx-background-color: transparent;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35px;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color:
            transparent
            transparent
            derive(-fx-base, 80%)
            transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: black;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal .split-pane-divider {
    -fx-background-color: transparent;
    -fx-border-color: transparent;
}

.split-pane {
    -fx-border-radius: 1;
    -fx-border-width: 1;
    -fx-background-color: transparent;
}

.list-view {
    -fx-background-insets: 0;
    -fx-padding: 0;
    -fx-background-color: transparent;

}

.list-cell {
    -fx-label-padding: 0 0 0 0;
    -fx-graphic-text-gap : 0;
    -fx-background-radius: 10 10 10 10;
    -fx-border-radius: 10 10 10 10;
    -fx-padding: 10px;
    -fx-background-insets: 3px, 3px;
    -fx-background-color: transparent
}

.list-cell:filled:even {
    -fx-background-color: #edf2f9;
}

.list-cell:filled:odd {
    -fx-background-color: #edf2f9;
}

.list-cell:filled:selected {
    -fx-background-color: #c0c5f9;
}

.list-cell .label {
    -fx-text-fill: black;
}

.cell_big_label {
    -fx-font-family: "Segoe UI Semibold";
    -fx-font-size: 16px;
    -fx-text-fill: #edf2f9;
}

.cell_small_label {
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13px;
    -fx-text-fill: #edf2f9;
}

.anchor-pane {
    -fx-background-color: transparent;
}

.pane-with-border {
    -fx-background-color: transparent;
    -fx-border-color: transparent;
    -fx-border-top-width: 1px;
}

.status-bar {
    -fx-background-color: transparent;
    -fx-text-fill: white;
}

.result-display {
    -fx-background-color: transparent;
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13pt;
    -fx-text-fill: black;
}

.result-display .label {
    -fx-text-fill: transparent !important;
}

.status-bar .label {
    -fx-font-family: "Segoe UI";
    -fx-text-fill: #edf2f9;
}

.status-bar-with-border {
    -fx-background-color: transparent;
    -fx-border-color: transparent;
    -fx-border-width: 1px;
}

.status-bar-with-border .label {
    -fx-text-fill: black;
}

.grid-pane {
    -fx-background-color: transparent;
    -fx-border-color: transparent;
    -fx-border-width: 1px;
}

.grid-pane .anchor-pane {
    -fx-background-color: transparent;
}

.calendar-panel .button {
    -fx-text-fill: #000000;
}

.calendar-panel {
    -fx-background-color: transparent;
    background-color: transparent;
}

.context-menu {
    -fx-background-color: derive(#070f60, 50%);
}

.context-menu .label {
    -fx-text-fill: #edf2f9;
}

.menu-bar {
    -fx-background-color: transparent;
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI";
    -fx-text-fill: #edf2f9;
    -fx-opacity: 0.9;
}

.menu .left-container {
    -fx-background-color: black;
}

/*
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
    -fx-background-color: white;
    -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color, 30%);
}

.dialog-pane {
    -fx-background-color: transparent;
}

.dialog-pane > *.button-bar > *.container {
    -fx-background-color: #ffffff;
}

.dialog-pane > *.label.content {
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-text-fill: black;
}

.dialog-pane:header *.header-panel {
    -fx-background-color: derive(#edf2f9, 25%);
}

.dialog-pane:header *.header-panel *.label {
    -fx-font-size: 18px;
    -fx-font-style: italic;
    -fx-fill: black;
    -fx-text-fill: black;
}

.scroll-bar {
    -fx-background-color: derive(#edf2f9, 20%);
    -fx-border-radius: 20px;
    -fx-background-radius: 20px;

}

.scroll-bar .thumb {
    -fx-background-color: derive(#070f60, 20%);

}

.scroll-bar .increment-button, .scroll-bar .decrement-button {
    -fx-background-color: transparent;
    -fx-rotate: 0;

}

.scroll-bar .increment-arrow {
    -fx-shape: "M 0 0 L 4 8 L 8 0 Z";
    -fx-background-color: #ffffff;
}

.scroll-bar .decrement-arrow {
    -fx-shape: "M 0 0 L 4 8 L 8 0 Z";
    -fx-background-color: #ffffff;
    -fx-rotate: -180;
}

.scroll-bar:vertical .increment-arrow, .scroll-bar:vertical .decrement-arrow {
    -fx-padding: 3 3 3 3;
}

.scroll-bar:horizontal .increment-arrow{
    -fx-rotate: -90;
    -fx-padding: 3 3 3 3;
}

.scroll-bar:horizontal .decrement-arrow {
    -fx-rotate: 90;
    -fx-padding: 3 3 3 3;
}

#cardPane {
    -fx-background-color: transparent;
    -fx-border-width: 10pt;
}

#commandTypeLabel {
    -fx-font-size: 11px;
    -fx-text-fill: #F70D1A;
}

#commandTextField {
    -fx-background-color: #edf2f9;
    -fx-background-insets: 0;
    -fx-border-color: #edf2f9 #edf2f9 black #edf2f9 ;
    -fx-border-insets: 0;
    -fx-border-width: 1;
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13pt;
    -fx-text-fill: black;
}

#filterField, #personListPanel, #personWebpage {
    -fx-effect: innershadow(gaussian, black, 10, 0, 0, 0);
}

#resultDisplay {
    -fx-background-color: transparent;
}

#resultDisplay .scroll-pane .viewport{
    -fx-background-color: transparent;
}

#resultDisplay .content {
    -fx-background-color: #edf2f9;
    -fx-background-radius: 0;
}
```
###### /resources/view/TagColour.css
``` css
Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
 */
#tags .teal {
    -fx-text-fill: white;
    -fx-background-color: #3e7b91;
}

#tags .red {
    -fx-text-fill: black;
    -fx-background-color: red;
}

#tags .yellow {
    -fx-background-color: yellow;
    -fx-text-fill: black;
}

#tags .blue {
    -fx-text-fill: white;
    -fx-background-color: blue;
}

#tags .orange {
    -fx-text-fill: black;
    -fx-background-color: orange;
}

#tags .brown {
    -fx-text-fill: white;
    -fx-background-color: brown;
}

#tags .green {
    -fx-text-fill: black;
    -fx-background-color: green;
}

#tags .pink {
    -fx-text-fill: black;
    -fx-background-color: pink;
}

#tags .black {
    -fx-text-fill: white;
    -fx-background-color: black;
}

#tags .grey {
    -fx-text-fill: black;
    -fx-background-color: grey;
}
```
###### /java/seedu/address/ui/CalendarPanel.java
``` java
    //Reused from https://github.com/CS2103AUG2017-T17-B2/main with modifications
    private void setTime() {
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
    }

    @Subscribe
    private void handleToggleCalendarViewEvent(ToggleCalendarViewEvent event) {
        Character c = event.viewMode;
        Platform.runLater(() -> toggleView(c));
    }

    public CalendarView getRoot() {
        return this.calendarView;
    }

    /**
     * Remove clutter from interface
     */
    private void disableViews() {
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowSearchField(false);
        calendarView.setShowSearchResultsTray(false);
        calendarView.setShowPrintButton(false);
        calendarView.setShowPageSwitcher(false);
        calendarView.setShowSourceTrayButton(false);
        calendarView.setShowPageToolBarControls(false);
        calendarView.setShowToolBar(false);
        calendarView.setShowSourceTray(false);

        calendarView.showDayPage();
    }

    /**
     * Changes calendar view accordingly
     */
    private void toggleView(Character c) {
        switch(c) {
        case ('d'):
            calendarView.showDayPage();
            return;
        case ('w'):
            calendarView.showWeekPage();
            return;
        case ('m'):
            calendarView.showMonthPage();
            return;
        default:
            //should not reach here
            assert (false);
        }
    }
```
###### /java/seedu/address/ui/TaskListPanel.java
``` java
//Reuse from PersonListPanel class with modification
/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private ListView<TaskCard> taskListView;

    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        setConnections(taskList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Task> taskList) {
        ObservableList<TaskCard> mappedList = EasyBind.map(taskList, (task) ->
                new TaskCard(task, taskList.indexOf(task) + 1));
        taskListView.setItems(mappedList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<TaskCard> {

        @Override
        protected void updateItem(TaskCard task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(task.getRoot());
            }
        }
    }

}
```
###### /java/seedu/address/ui/AppointmentListPanel.java
``` java
//Reuse from PersonListPanel class with modification
/**
 * Panel containing the list of appointments.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private ListView<AppointmentCard> appointmentListView;

    public AppointmentListPanel(ObservableList<Appointment> appointmentList) {
        super(FXML);
        setConnections(appointmentList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Appointment> appointmentList) {
        ObservableList<AppointmentCard> mappedList = EasyBind.map(appointmentList, (appointment) ->
                        new AppointmentCard(appointment, appointmentList.indexOf(appointment) + 1));
        appointmentListView.setItems(mappedList);
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code AppointmentCard}.
     */
    class AppointmentListViewCell extends ListCell<AppointmentCard> {

        @Override
        protected void updateItem(AppointmentCard appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(appointment.getRoot());
            }
        }
    }

}
```
###### /java/seedu/address/ui/PersonCard.java
``` java
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    /**
     * Returns the color style for {@code tagName}'s label.
     */
    private void initTags(Person person) {
        person.getTags().forEach(tag -> {
            Label tagLabel = new Label(tag.tagName);
            tagLabel.getStyleClass().add(tag.tagColorStyle);
            tags.getChildren().add(tagLabel);
        });
    }

    /**
     * Sets the text of  the {@code subjects} label with all the {@code Subject} of the student's classes.
     */
    private void initSubjects(Student student) {
        StringJoiner joiner = new StringJoiner(", ");
        for (Subject subject: student.getSubjectList()) {
            joiner.add(subject.toString());
        }
        subjects.setText("Subjects: " + joiner.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
```
###### /java/seedu/address/model/tag/Tag.java
``` java
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    private static final String[] TAG_COLOR_STYLES = {"teal", "red", "yellow", "blue", "orange", "brown",
        "green", "pink", "black", "grey"};
```
###### /java/seedu/address/model/tag/Tag.java
``` java
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    /**
     * Returns a color style for {@code tagName}
     */
    private String getTagColorStyle(String tagName) {
        // we use the hash code of the tag name to generate a random color, so that the color remain consistent
        // between different runs of the program while still making it random enough between tags.
        return TAG_COLOR_STYLES[Math.abs(tagName.hashCode()) % TAG_COLOR_STYLES.length];
    }


}
```

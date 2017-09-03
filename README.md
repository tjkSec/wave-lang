Wave is an upcoming css preprocessor that mimics es6 in syntax, here is an example of variables...

```css
let backgroundColour = #292D3E;
let textColour = #eeeeee;
let myFont = 'Somatic', sans-serif;

.title-wrapper {
    font-family: myFont;
    color: textColour;
    background: backgroundColour;
}
```

would compile into...

```css
`/* ~ Wave Variable ~ */
/* ~ Wave Variable ~ */
/* ~ Wave Variable ~ */

.title-wrapper {
    font-family: 'Somatic', sans-serif;
    color: #eeeeee;
    background: #292D3E;
}```

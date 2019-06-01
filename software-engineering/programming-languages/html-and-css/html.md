## HTML

### General

* URL = Uniform Resource Locator

##### Meta

* `description`: max 155 char; used by search engines.
* `keywords`: comma-separated. No effect on search engine indexing.
* `robots`: should search engines add this to search results?
  - value: `noindex`: don't add page to search results
  - value: `nofollow`: add this page to results, but not any linked pages
* `author`
* `pragma`: prevents browser from caching page (caching: store locally to save time downloading contents in future)
* `expires`: indication of when page should expire & not be cached

```html
<head>
  <title>Some title</title>
  <meta name="description" content="Page's description" />
  <meta name="keywords" content="page, description, keywords, yeah" />
  <meta name="robots" content="nofollow" />
  <meta http-equiv="author" content="Chelsea" />
  <meta http-equiv="pragma" content="no-cache" />
  <meta http-equiv="expires" content="Some date in particular format" />
</head>
```



### Video

##### The file itself
You need to provide the video in multiple formats for cross-browser support.

* `H264` IE & Safari
* `WebM` Android, Chrome, Firefox, Opera

```html
<video poster="image.jpg" width="400" height="320" preload controls loop>
  <source src="videos/video.mp4" type ="video/mp4; codecs='avc1.42E01E, mp4a.40.2'" />
  <source src="videos/video.webm" type="video/webm;codecs='vp8, vorbis'">
  <p>Description of video in case it didn't load.</p>
</video>

```

##### Attributes

* `src`: path to the video
* `poster`: specify image to show while video downloading, or before user presses play
* `width`, `height`: Units are pixels.
* `preload`: What the browser should do when page loads; Values include...
  - `none`: Don't load until user presses play
  - `auto`: Download the video when page loads
  - `metadata`: Only collect size, first frame, tracklist, & duration
* `controls`: No value. Its presence says browser should supply own controls for playback.
* `autoplay`: No value.
* `loop`: No value.

### Audio

##### The file itself
You need to provide the audio in multiple formats for cross-browser support.

* `MP3`: Safari 5+, Chrome 6+, IE9
* `Ogg Vorbis`: Firefox, Chrome 6, Opera 1.5, IE9

```html
<audio controls autoplay>
  <source src="audio/audio.ogg" />
  <source src="audio/audio.mp3" />
  <p>A description of the audio in case it doesn't load, or isn't supported.</p>
</audio>
```

##### Attributes

* `src`: path to auto file.
* `controls`: No value. Its presence says that the browser should supply its own controls for playback. You can make your own via JS.
* `autoplay`: No value.
* `preload`: same values as video
* `loop`: No value.



### Forms

* `action`: the URL of the page that'll receive the information in the form when it's submitted
* `method`: either [get] or [post].
  - `get`: used for things like short forms (search), and info retrieval
    + default method used when unspecified
  - `post`: used to manipulate database information, and when contains sensitive data

```html
<form action="http://site.com/login.php" method="get" accept-charset="utf-8">
</form>
```

##### Input
* `name`: the name the server looks for to know which information is which
* `maxlength`: limit number of characters
* `placeholder`
* `value`

```html
<form action="http://foo.bar/login.php">
  <p>Username:
    <input type="color" maxlength="" name="" value="" placeholder="">
    <input type="image" name="" value="" placeholder="">
    <input type="month" name="" value="" placeholder="">
  </p>
</form>
```

__Radio button__:
Canont be deselected once selected. Value isn't displayed.
```html
<input type="radio" name="food" value="icecream" placeholder="" />Ice cream<br>
<input type="radio" name="food" value="cookies" /> Cookies<br>
<input type="radio" name="food" value="seaweed" checked="checked" />Seaweed
```

__Checkbox__:
```html
Which food do you like?<br/><br/>
<input type="checkbox" name="food" value="icecream">Ice cream<br/>
<input type="checkbox" name="food" value="cookies">Cookies<br/>
<input type="checkbox" name="food" value="seaweed" checked="checked">Seaweed<br/>
```

__Drop down__
Basic choose 1 selection drop down
```html
Which country begins with U?
<select name="countries">
  <option value="unitedstates" disabled>United States</option>
  <option value="someother" selected>Some other country</option>
  option
</select>
```

Slightly more advanced multiple selection drop down. Shows you 3 options at a time.

```html
<select name="" size="3" multiple>
  <option value="dog">dog</option>
  <option value="cat">cat</option>
  <option value="mouse">mouse</option>
  <option value="fish">fish</option>
</select>
```

More input types:

* Hidden
* Password
* Submit
* [Etc...](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Input)

##### Labeling inputs

```html
<!-- Use labels to wrap around input -->
<label>Name: <input type="text" name="name" /></label> <br />

<!-- Use labels for specific inputs -->
<input id="female" type="radio" name="gender" value="f">
<label for="female">Female</label>

<input id="male" type="radio" name="gender" value="m">
<label for="male">Male</label>

<input id="other" type="radio" name="gender" value="nyu">
<label for="other">Other</label>
```

##### Group form elements
```html
<fieldset>
  <legend>Name of set</legend>
  ... Your form section here ...
</fieldset>
```

##### Text area
```html
<textarea name="comments" cols="20" rows="5" placeholder="yea">
  
</textarea>
```

### Tables

A table is written out by row.

* `<thead>` table header: holder of the table's headings
* `<th>` table heading
* `<tbody>` table's body: holds all of the rows w/ info
* `<tr>` table row
* `<td>` each cell of a table (many used w/i a `tr`)
  - `colspan` td attribute to indicate how many columns the cell spans
* `rowspan` td attribute to indicate how many rows the cell spans
* `<tfoot>` table footer

```html
<table>
  <thead>
    <tr>
      <th></th>
      <th scope="col">Odd</th>
      <th scope="col">Even</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">First</th>
      <td colspan="2">Item 1 & 2</td>
    </tr>
    <tr>
      <th scope="row">Second</th>
      <td>Item 3</td>
      <td>Item 4</td>
    </tr>
    <tr>
      <th scope="row" rowspan="2"></tr>
      <td rowspan="2">Item 5 <br/> Item 7</td>
      <td rowspan="2">Item 5 <br/> Item 8</td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td rowspan="3">This is the footer.</td>
    </tr>
  </tfoot>
</table>
```



### Miscellaneous 

##### Escape characters
Don't be lazy.

* `&amp;` &
* `&quot;` Quotation mark
* `&rdquo;` Right double quotes
* `&ldquo;` Left double quotes
* `&rsquo;` Right single quote
* `&lsquo;` Left single quote
* `&lt;` Less-than sign

Other: `&lt;` less-than sign, `&gt;` greater-than sign, `&cent;` cent sign, `&pound;` pound sign, `&copy;` copyright symbol, `&reg;` registered trademark, `&trade;` trademark, `&times;` multiplication sign, `&divide;` division sign 

### Super Miscellaneous

##### Subscript & superscript
```html
5<sup>th</sup>
```

```html
H<sub>2</sub>
```

##### Quotes

```html
Foo said, <q cite="http://bar.bar">Yo, wut up.</q>
```

##### Acronyms & abbreviations

```html
<acronym title="National Aeronautics and Space Administration">NASA</acroynm>
```

```html
<abbr title="Professor">Prof</abbr>
```

##### Cite & define

Use cite to cite things like books. Texts. The inner HTML will appear italicized.
```html
<cite>HTML & CSS Book</cite> by Duckett
```

Use cite to introduce new terms (eg. scientific). Some browsers italicize them.
```html
A <dfn>duck</dfn> is a duck.
```

##### Address

Display emails, location addresses, etc.
```html
<address>
  <a href="mailto:email@email.email">Email owner</a><br>
  <p>79 Their address; City, State 51PC0D3.</p>
</address>
```

##### Edits: insertions, deletions, etc.

Show what was inserted & what was deleted.
```html
<p>You are a <del>cat</del> <ins>duck</ins>.</p>
```

Show that something is no longer accurate/relevant, but shouldn't be deleted
```html
<p><s>You're cool</s></p>
<p>Uh... what do I put here?</p>
```

##### Definition lists

* `<dt>` definition term
* `<dd>` definition definition lool.

```html
<dl>
  <dt>Duck</dt>
  <dd>An animal that is a duck.</dd>
  
  <dt>Elephant</dt>
  <dd>An animal that is an elephant.</dd>
</dl>
```

##### Img attributes

* `alt` description of what's in the image
* `title` info shown when hovering over image

##### Figures & figure captions (Images w/ subtitles)

```html
<figure>
  <img src="duckie.svg" alt="a duckie" title="duckie">
  <br />
  <img src="anotherduckie.svg" alt="another duckie" title="duckie2">
  <br />
  <figcaption>Fig tree. Duckie.</figcaption>
</figure>
```

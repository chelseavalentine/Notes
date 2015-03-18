#### Syntax
Link a favicon (located in assets/images
```Ruby
<%= favicon_link_tag 'favicon.png', type: 'image/png', sizes '16x16', width: '16px' %>
```

img:
```Ruby
<%= image_tag ("image.svg", class: "", id: "", alt: "") %>
```

a: (method isn't usually used, I've seen it used for logging out, where method: "delete"
```Ruby
<%= link_to "linked text here", route_name_path_thing, class: "", method: "" %>
```

External stylesheet
```Ruby
<%= stylesheet_link_tag 'whereYouAreImportingItToLikeApplication', 'external URL', type: 'text/css' %>
```

button: It works but it behaves nothing as you would expect it to
```Ruby
<%= button_to "buttontext", path_name_here, :method => "get" %>
```

External javascript (relative paths are relative to assets/javascripts)
```Ruby
<%= javascript_include_tag 'externalURL.script hereee' %>
```

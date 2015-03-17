#### Syntax
Link a favicon (located in assets/images
```Rails
<%= favicon_link_tag 'favicon.png', type: 'image/png', sizes '16x16', width: '16px' %>
```

Image
```Rails
<%= image_tag ("image.svg", class: "", id: "", alt: "") %>
```

Link (method isn't usually used, I've seen it used for logging out, where method: "delete"
```Rails
<%= link_to "linked text here", route_name_path_thing, class: "", method: "" %>
```

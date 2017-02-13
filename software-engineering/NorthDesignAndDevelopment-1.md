# North: Design and Development Guide

### Development process

#### Agile scrum

* benefit statement: _As [persona], I want [desire] so that [rationale]._

#### Preprocessed languages

* the compiled output of preprocessed languages (eg. Sass) should be gitignored, and built upon deployment

### Content strategy

* __content strategy__: the processes of analyzing, sorting, constructing, and placing content

#### Project vision

* vision statements state the goal and the features that'll best serve the content
  - answers the following questions:
    + target customer? product consumer? product administer and maintainer?
    + needs the product addresses?
    + attributes critical to success?
    + comparison to existing products, unique selling points?

#### User stories

* to get a better understanding of users, and how your product could relate, ask questions like:
  - what do you find most valuable about the existing product/similar ones/competitors' products?
  - is anything of value missing from the existing product & similar products?
  - how do you most often access the product?
  - pain points?
  - what's the target demographic information?

* don't use stereotypes, only use the statistical analysis of the interviews

* user personas comprise of:
  - name
  - description of typical use of product
  - motivator for use of product (primary, secondary, tertiary needs)
  - pain points with the product

#### Content inventory

* analysis of existing content (or an inventory based on perceived content needs, if non-existent)
  - create a spreadsheet with the intrinsic (title, owner, last updated) and analytics (page views, rank, notes) data
  - content = images, videos, charts, forms, text, etc.

* doesn't need to be all content, but a statistically significant amount

* create limits for each content type, depending on the type
  - * = required
  - (0) = soft limit, when a known minimum exists but no known limit
  - 000x00 = dimensions, w x h
  - ^foo = begins with
  - bar$ = ends with
  - .jpg|.png = multiple options for file extensions

#### Content audit

* determining whether the current content is worth keeping, editing, or removing


* questions to ask:
  - is it too long? short? just right? Can longer content be cut into shorter chunks and still make sense?
  - is the copy wordy? rambling? Can it be cleaned up and hold its meaning?
  - does each page/chunk get to the point quickly?
  - is the content evenly broken up?
  - is the content relevant and important?

* do a __gap analysis__ of the content based on the answers (divided into following categories:)
  - _keep_: as is
  - _revise_: edit to tighten up copy & content types
  - _delete_: outdated, irrelevant, not useful
  - _create new_: based on business needs, user interviews

#### Content modeling

* __content model__: overview of different types of content, modeled to include its (visible and structural) attributes, containing this info:
  - content type _title_
  - _description_
  - _benefit statement_
  - _value_ of the content type
    + additional aspects: resources required to build content, like advertising revenue
  - _attributes_, each with a data limit (eg. character limit, date format)
  - _relationships_ the content type has to other types of content

#### Information architecture

* __information architecture (IA)__: process of determining what content gets used when, where, and why

* things to keep in mind while building your IA:
  - truncation isn't a content strategy
    + content requiring truncation usually isn't written for summary or reuse
    + usually doesn't contain trigger words
    + never truncate headlines
    + always provide summaries for long copy
    + provide alternate copy when needed
  - build systems of content
    + allow for different sizes and styles of content attributes
      - eg. small, medium, large images
      - eg. short & long human readable, and SEO-friendly headlines
    + do _not_ build content for specific contexts (eg. iPhones, Androids, tablets, desktops)
  - content should be easily navigable
    + don't paginate unnecessarily
    + make navigating to sections in long pieces of content easy
    + provide enough context for a user to make their own navigation decisions
      - provide plenty of trigger words
    + keep navigation uncluttered
  - content should be available
    + don't restrict content, especially based on device
    + provide alternate formats of content if one format can't be made available
    + don't store content as HTML, but rather as attributes that can be presented in multiple ways
    + make content available and in a way best suited for the device

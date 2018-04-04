# Sisyphus25-reused
###### /java/guitests/guihandles/PersonCardHandle.java
``` java
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    /**
     *
     * @param tag Text value of the tag label
     * @return List of style classes for tag label with text value {@code tag}
     */
    public List<String> getTagStyleClasses(String tag) {
        return tagLabels
                .stream()
                .filter(label -> label.getText().equals(tag))
                .map(Label::getStyleClass)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such tag."));
    }
}
```

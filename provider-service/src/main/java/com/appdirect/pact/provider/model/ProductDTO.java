package com.appdirect.pact.provider.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Product model
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-27T11:45:17.296-04:00")

public class ProductDTO {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("content")
  private String content = null;

  public ProductDTO id(String id) {
    this.id = id;
    return this;
  }

   /**
   * The product id
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public ProductDTO name(String name) {
    this.name = name;
    return this;
  }

  public ProductDTO content(String content) {
    this.content = content;
    return this;
  }

   /**
   * The product name
   * @return name
  **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  /**
   * The product name
   * @return name
   **/
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDTO productWsDTO = (ProductDTO) o;
    return Objects.equals(this.id, productWsDTO.id) &&
        Objects.equals(this.name, productWsDTO.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDTO {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


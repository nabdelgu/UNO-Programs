require "rails_helper"

RSpec.describe RailsController, type: :routing do
  describe "routing" do

    it "routes to #index" do
      expect(:get => "/rails").to route_to("rails#index")
    end

    it "routes to #new" do
      expect(:get => "/rails/new").to route_to("rails#new")
    end

    it "routes to #show" do
      expect(:get => "/rails/1").to route_to("rails#show", :id => "1")
    end

    it "routes to #edit" do
      expect(:get => "/rails/1/edit").to route_to("rails#edit", :id => "1")
    end

    it "routes to #create" do
      expect(:post => "/rails").to route_to("rails#create")
    end

    it "routes to #update via PUT" do
      expect(:put => "/rails/1").to route_to("rails#update", :id => "1")
    end

    it "routes to #update via PATCH" do
      expect(:patch => "/rails/1").to route_to("rails#update", :id => "1")
    end

    it "routes to #destroy" do
      expect(:delete => "/rails/1").to route_to("rails#destroy", :id => "1")
    end

  end
end

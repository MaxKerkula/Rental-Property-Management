 <template>
  
  <div class="container">
      <header>
          Header Content
      </header>

      <nav>
          <div class="content">
              Navigation
              <div><router-link :to="{name: 'home'}">Home</router-link></div>
              <div><router-link :to="{name: 'about'}">About</router-link></div>
              <div><router-link :to="{name: 'contact'}">Contact Us</router-link></div>
        </div>
      </nav>
      <main>
          <div class="content">
               <!-- different views/pages are displayed/injected here -->
                <router-view></router-view>  
          </div>     
      </main>
      <aside>
          <div class="content">
              Sidebar / Info
          </div>
      </aside>
      <footer>
          <div class="content">
              Footer content
          </div>
      </footer>
  </div>
</template>

<script>
    
   
  export default {
   
  };
</script>

<style>
    body {
        margin: 0;
    }

    /* Add CSS Grid here  background colors: Header - #f69a00;  Nav - #f2ead2;  
    main - #f9f5f0;  aside - #f2ead2;  footer - #331312;
    font colors header, footer - #ffffff;  nav, main, aside - #74889a;*/

    .container{
        display: grid;

        grid-template-areas: 
            "header header header" 
            "nav content side"
            "footer footer footer";

        grid-template-columns: 200px 1fr 200px;
        grid-template-rows: 125px 1fr 85px;
        gap: 10px;

        height: 100vh;


    }
    /*handle responsive behavior */
    @media (max-width: 768px) {
        .container{
            grid-template-areas: 
                "header"
                "nav"
                "content"
                "side"
                "footer";
            grid-template-columns: 1fr;
            grid-template-rows:
                auto /*Header */
                75px /*Nav*/
                1fr /* content */
                75px /* Sidebar */
                auto; /* Footer */
            
        }
        header, footer, nav, main, aside {
            padding: 5px 0;
        }
        nav, aside {
        margin: 0;
        }

    }

    header {
    grid-area: header;
    background-color: #360145;
    }

    nav {
        grid-area: nav;
        margin-left: 0.5rem;
        background-color: #f2ead2;
    }

    main {
        grid-area: content;
        background-color: #f9f5f0;
    }

    aside {
        grid-area: side;
        margin-right: 0.5rem;
        background-color: #f2ead2;
    }

    footer {
        grid-area: footer;
        background-color: #D9D9D9;
    }

    header, footer {
        color: #ffffff;
    }
    nav, main, aside {
        color: #74889a;
    }
    header, footer, nav, main, aside {
        font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        font-size: 24px;
        text-transform: uppercase;
    }

    div.content {
        text-align: center;
        padding-top: 20px;
    }
</style>
